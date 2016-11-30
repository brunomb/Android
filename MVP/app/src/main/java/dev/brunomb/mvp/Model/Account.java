package dev.brunomb.mvp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brunomiranda on 21/11/16.
 */
public class Account {

    @SerializedName("email")
    String email;
    @SerializedName("senha")
    String senha;
    @SerializedName("cpf")
    String cpf;
    @SerializedName("serial_central")
    String serialCentral;

    public Account(String email, String senha, String cpf, String serialCentral) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.serialCentral = serialCentral;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSerialCentral() {
        return serialCentral;
    }

    public class CreateAccountResponse {
        int status;
        String error;

        public String getError() {
            return error;
        }

        public int getStatus() {
            return status;
        }
    }
}
