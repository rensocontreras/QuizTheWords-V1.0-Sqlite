package com.contreras.myquizapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.ItemEntity.ItemCompetidorTop;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.Entity.Pregunta;
import com.contreras.myquizapplication.Entity.Top;
import com.contreras.myquizapplication.Entity.Usuario;
import com.contreras.myquizapplication.R;
import com.contreras.myquizapplication.Util.Constantes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "game.sqlite";
    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    private Context context;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        try {

            db = getWritableDatabase();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ejecutarScript(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void ejecutarScript(SQLiteDatabase sqLiteDatabase) {

        String linea="";

        InputStream inputStream = context.getResources().openRawResource(R.raw.script);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        if(inputStream!=null){

            try {
                while ((linea=reader.readLine()) != null) {
                        sqLiteDatabase.execSQL(linea);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public ArrayList<Nivel> obtenerNiveles() {

        ArrayList<Nivel> list_niveles = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT numero_nivel,tot_preguntas,limite_preguntas FROM Nivel", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                    Nivel myNivel = new Nivel();
                    myNivel.setNumero_nivel(cursor.getInt(cursor.getColumnIndex(Constantes.N_NUMERO_NIVEL)));
                    myNivel.setTot_preguntas(cursor.getInt(cursor.getColumnIndex(Constantes.N_TOT_PREGUNTAS)));
                    myNivel.setLimite_preguntas(cursor.getInt(cursor.getColumnIndex(Constantes.N_LIMITE_PREGUNTAS)));

                list_niveles.add(myNivel);
            }while(cursor.moveToNext());
        }
        return list_niveles;
    }

    public void actualizarCompeticionActual(int miNumeroUsuario,int miNumeroNivel, int misPreguntasResueltas){
        //TODO, ARREGLAR NUMERO DE PREGUNTAS RESUELTAS
        ContentValues cv = new ContentValues();
        cv.put(Constantes.C_PREGUNTAS_RESUELTAS,misPreguntasResueltas);

        db.update("Compite",cv,"numero_nivel ='"+miNumeroNivel+"' and codigo_usuario ='"+miNumeroUsuario+"'",null);

    }

    public void actualizarCompeticionSiguiente(int miNumeroUsuario, int miNumeroNivel, int stadoCandado){
        //TODO, ARREGLAR ESTADO CANDADO
        ContentValues cv = new ContentValues();
        cv.put(Constantes.C_ESTADO_CANDADO,stadoCandado);
        db.update("Compite",cv,"numero_nivel ='"+miNumeroNivel+"' and codigo_usuario ='"+miNumeroUsuario+"'",null);
    }

    public boolean existenciaNivelSiguiente(int numeroNivel) {
        Cursor cursor = db.rawQuery("SELECT numero_nivel,tot_preguntas,limite_preguntas FROM Nivel WHERE numero_nivel ='" + numeroNivel + "'", null);

        if (cursor != null && cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Pregunta> obtenerPreguntas(int position) {

        ArrayList<Pregunta> list_preguntas = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT numero_pregunta,numero_nivel,contenido_pregunta,opcion1,opcion2,opcion3,opcion4,respuesta FROM Pregunta Where numero_nivel ='"+position+"'"+ " ORDER BY numero_pregunta", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                Pregunta myPregunta = new Pregunta();
                myPregunta.setNumero_pregunta(cursor.getInt(cursor.getColumnIndex(Constantes.P_NUMERO_PREGUNTA)));
                myPregunta.setNumero_nivel(cursor.getInt(cursor.getColumnIndex(Constantes.P_NUMERO_NIVEL)));
                myPregunta.setContenido_pregunta(cursor.getString(cursor.getColumnIndex(Constantes.P_CONTENIDO_PREGUNTA )));
                myPregunta.setOpcion1(cursor.getString(cursor.getColumnIndex(Constantes.P_OPCION1)));
                myPregunta.setOpcion2(cursor.getString(cursor.getColumnIndex(Constantes.P_OPCION2)));
                myPregunta.setOpcion3(cursor.getString(cursor.getColumnIndex(Constantes.P_OPCION3)));
                myPregunta.setOpcion4(cursor.getString(cursor.getColumnIndex(Constantes.P_OPCION4)));
                myPregunta.setRespuesta(cursor.getString(cursor.getColumnIndex(Constantes.P_RESPUESTA)));


                list_preguntas.add(myPregunta);
            }while(cursor.moveToNext());
        }

        return list_preguntas;
    }

    public void insertarUsuario(Usuario usuario){

        ContentValues cv = new ContentValues();
        cv.put(Constantes.U_NOMBRES, usuario.getNombres());
        cv.put(Constantes.U_APELLIDOS, usuario.getApellidos());
        cv.put(Constantes.U_CORREO, usuario.getCorreo());
        cv.put(Constantes.U_PASSWORD, usuario.getPassword());
        cv.put(Constantes.U_SEXO, usuario.getSexo());

        db.insert("Usuario",null,cv);
        insertCompetir(usuario.getCorreo(),usuario.getPassword());
    }

    public ArrayList<Usuario> obtenerUsuarios(){

        ArrayList<Usuario> list_usuarios = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM Usuario", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                Usuario myUsuario = new Usuario();
                myUsuario.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex(Constantes.U_CODIGO_USUARIO)));
                myUsuario.setNombres(cursor.getString(cursor.getColumnIndex(Constantes.U_NOMBRES)));
                myUsuario.setApellidos(cursor.getString(cursor.getColumnIndex(Constantes.U_APELLIDOS)));
                myUsuario.setCorreo(cursor.getString(cursor.getColumnIndex(Constantes.U_CORREO)));
                myUsuario.setPassword(cursor.getString(cursor.getColumnIndex(Constantes.U_PASSWORD)));
                myUsuario.setSexo(cursor.getString(cursor.getColumnIndex(Constantes.U_SEXO)));

                list_usuarios.add(myUsuario);
            }while(cursor.moveToNext());
        }
        return list_usuarios;
    }




    public void insertCompetir(String correo, String password) {
        ContentValues cv;

        Usuario usuario = validarUsuario(correo,password);
        ArrayList<Nivel> list_niveles = obtenerNiveles();
        int estado_candado = 1;

        for (Nivel n:list_niveles) {
            cv = new ContentValues();
            cv.put(Constantes.C_CODIGO_USUARIO,usuario.getCodigoUsuario());
            cv.put(Constantes.C_NUMERO_NIVEL,n.getNumero_nivel());
            cv.put(Constantes.C_ESTADO_CANDADO,estado_candado);
            cv.put(Constantes.C_PREGUNTAS_RESUELTAS,0);
            cv.put(Constantes.C_MY_TIMER,100);
            estado_candado = 0;
            db.insert("Compite",null,cv);
        }
    }

    public ArrayList<Compite> obtenerCompeticion(int myCodigoUsuario) {
        ArrayList<Compite> list_competiciones = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT codigo_usuario,numero_nivel,estado_candado,preguntas_resueltas,my_timer FROM Compite WHERE codigo_usuario='"+myCodigoUsuario+"'", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                Compite miCompeticion = new Compite();
                miCompeticion.setCodigo_usuario(cursor.getInt(cursor.getColumnIndex(Constantes.C_CODIGO_USUARIO)));
                miCompeticion.setNumero_nivel(cursor.getInt(cursor.getColumnIndex(Constantes.C_NUMERO_NIVEL)));
                miCompeticion.setEstado_candado(cursor.getInt(cursor.getColumnIndex(Constantes.C_ESTADO_CANDADO)));
                miCompeticion.setPreguntas_resueltas(cursor.getInt(cursor.getColumnIndex(Constantes.C_PREGUNTAS_RESUELTAS)));
                miCompeticion.setMy_timer(cursor.getInt(cursor.getColumnIndex(Constantes.C_MY_TIMER)));

                list_competiciones.add(miCompeticion);
            }while(cursor.moveToNext());
        }

        return list_competiciones;
    }

    public Usuario validarUsuario(String mycorreo, String mypassword) {
        Usuario myUsuario = null;

        Cursor cursor = db.rawQuery("SELECT codigo_usuario, nombres, apellidos, correo,password,sexo "+
                                        "FROM Usuario "+
                                        "WHERE correo='"+mycorreo+"'"+"and password='"+mypassword+"'", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                myUsuario = new Usuario();
                myUsuario.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex(Constantes.U_CODIGO_USUARIO)));
                myUsuario.setNombres(cursor.getString(cursor.getColumnIndex(Constantes.U_NOMBRES)));
                myUsuario.setApellidos(cursor.getString(cursor.getColumnIndex(Constantes.U_APELLIDOS)));
                myUsuario.setCorreo(cursor.getString(cursor.getColumnIndex(Constantes.U_CORREO)));
                myUsuario.setPassword(cursor.getString(cursor.getColumnIndex(Constantes.U_PASSWORD)));
                myUsuario.setSexo(cursor.getString(cursor.getColumnIndex(Constantes.U_SEXO)));
            }while(cursor.moveToNext());
        }
        return myUsuario;
    }

    public void actualizarTimer(int codigoUsuario,int numeroNivel,int numTimer){
        ContentValues cv = new ContentValues();
        cv.put(Constantes.C_MY_TIMER,numTimer);

        db.update("Compite",cv,"codigo_usuario='"+codigoUsuario+"' and numero_nivel='"+numeroNivel+"'",null);
    }



    public Top obtenerCompetidorTop(int myNumeroNivel){
        Top competidorTop = null;

        Cursor cursor = db.rawQuery("SELECT * FROM Top Where numero_nivel ='"+myNumeroNivel+"'", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            competidorTop = new Top();
            competidorTop.setNumero_nivel(cursor.getInt(cursor.getColumnIndex(Constantes.T_NUMERO_NIVEL)));
            competidorTop.setCodigo_usuario(cursor.getInt(cursor.getColumnIndex(Constantes.T_CODIGO_USUARIO)));
            competidorTop.setMy_timer(cursor.getInt(cursor.getColumnIndex(Constantes.T_MY_TIMER)));
            competidorTop.setPreguntas_resueltas(cursor.getInt(cursor.getColumnIndex(Constantes.T_PREGUNTAS_RESUELTAS)));
        }
        return competidorTop;
    }

    public void insertarCompetidor(Top competidorTop){
        ContentValues cv = new ContentValues();
        cv.put(Constantes.T_NUMERO_NIVEL,competidorTop.getNumero_nivel());
        cv.put(Constantes.T_CODIGO_USUARIO, competidorTop.getCodigo_usuario());
        cv.put(Constantes.T_MY_TIMER, competidorTop.getMy_timer());
        cv.put(Constantes.T_PREGUNTAS_RESUELTAS, competidorTop.getPreguntas_resueltas());

        db.insert("Top",null,cv);
    }

    public void actualizarCompetidorTop(Top competidorTop){
        ContentValues cv = new ContentValues();
        cv.put(Constantes.T_CODIGO_USUARIO,competidorTop.getCodigo_usuario());
        cv.put(Constantes.T_MY_TIMER,competidorTop.getMy_timer());
        cv.put(Constantes.T_PREGUNTAS_RESUELTAS,competidorTop.getPreguntas_resueltas());

        db.update("Top",cv,"numero_nivel='"+competidorTop.getNumero_nivel()+"'",null);
    }

    public ArrayList<Top> obtenerCompetidoresTop(){
        ArrayList<Top> list_competidores = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from Top", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                Top myCompetidor = new Top();
                myCompetidor.setNumero_nivel(cursor.getInt(cursor.getColumnIndex(Constantes.T_NUMERO_NIVEL)));
                myCompetidor.setCodigo_usuario(cursor.getInt(cursor.getColumnIndex(Constantes.T_CODIGO_USUARIO)));
                myCompetidor.setMy_timer(cursor.getInt(cursor.getColumnIndex(Constantes.T_MY_TIMER)));
                myCompetidor.setPreguntas_resueltas(cursor.getInt(cursor.getColumnIndex(Constantes.T_PREGUNTAS_RESUELTAS)));
                list_competidores.add(myCompetidor);
            }while(cursor.moveToNext());
        }
        return list_competidores;
    }

    public ArrayList<ItemCompetidorTop> obtenerItemCompetidoresTop(){
        ArrayList<ItemCompetidorTop> list_competidores = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select t.numero_nivel,t.codigo_usuario,u.nombres,u.sexo,t.my_timer,t.preguntas_resueltas from Top t join Usuario u on t.codigo_usuario=u.codigo_usuario", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                ItemCompetidorTop myCompetidor = new ItemCompetidorTop();
                myCompetidor.setNumero_nivel(cursor.getInt(cursor.getColumnIndex(Constantes.IT_NUMERO_NIVEL)));
                myCompetidor.setCodigo_usuario(cursor.getInt(cursor.getColumnIndex(Constantes.IT_CODIGO_USUARIO)));
                myCompetidor.setNombres(cursor.getString(cursor.getColumnIndex(Constantes.IT_NOMBRES)));
                myCompetidor.setSexo(cursor.getString(cursor.getColumnIndex(Constantes.IT_SEXO)));
                myCompetidor.setMy_timer(cursor.getInt(cursor.getColumnIndex(Constantes.IT_MY_TIMER)));
                myCompetidor.setPreguntas_resueltas(cursor.getInt(cursor.getColumnIndex(Constantes.IT_PREGUNTAS_RESUELTAS)));
                list_competidores.add(myCompetidor);
            }while(cursor.moveToNext());
        }
        return list_competidores;
    }


}




