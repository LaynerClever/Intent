package com.example.layner.intent;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final int PICK_CONTACT = 2015;

    public void intentExplicita(View view) {
        Intent intent = new Intent(MainActivity.this, SegundaTela.class);
        startActivity(intent);
    }

    public void ligarNumero(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:+5564992793639"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    public void buscaListaContato(View view){
        Intent intent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 001);
    }

    public void definirAlarme(View view){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Meu alarme app android");
        intent.putExtra(AlarmClock.EXTRA_HOUR, 6);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        startActivity(intent);
    }

    public void contatoDetalhe(View view){
        //Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);

        Uri uri = Uri.parse("content://com.android.contacts/contacts/");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 007);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 007) {
            if (resultCode == RESULT_OK) {
                Uri contactUri = data.getData();
                Intent intentExibirContato = new Intent(Intent.ACTION_VIEW, contactUri);
                startActivity(intentExibirContato);
            }
        }}

    public void abrirMaps(View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=28.358 -81.59 (\" + Disney's Animal Kingdom + \")"));
        startActivity(intent);
    }

    public void intentFilterComActionSend(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("Text/Plain");
        startActivity(intent);
    }

    public void abrirCamera(View view){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    public void enviarEmail(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:laynerclever@gmail.com"));
        startActivity(emailIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
