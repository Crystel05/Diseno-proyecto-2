package Modelo;

import java.io.Serializable;
import java.util.HashMap;

//Se envia al enemigo despues de atacarlo
public class AttackInfo implements Serializable {
    String characterName;
    String weaponName;
    HashMap<String,Integer> damageToTeam;

    public AttackInfo(String characterName,String weaponName){
        this.characterName = characterName;
        this.weaponName = weaponName;
    }

    public void addToDamageToTeam(String nombre,int dano){
        damageToTeam.put(nombre,dano);
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public HashMap<String, Integer> getDamageToTeam() {
        return damageToTeam;
    }

    public void setDamageToTeam(HashMap<String, Integer> damageToTeam) {
        this.damageToTeam = damageToTeam;
    }
}
