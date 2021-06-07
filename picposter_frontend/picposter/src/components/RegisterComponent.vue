<template>
    <div>
      <form @submit.prevent="handleSubmit">
          <!-- Email input -->
          <div class="form-outline mb-4">
            <input type="email" id="form1Example1" class="form-control"
              v-model="email"
              :class="{'has-error' : submitting && invalidEmail}" />
            <label class="form-label" for="form1Example1">Adres Email</label>
          </div>

          <!-- Username input -->
          <div class="form-outline mb-4">
            <input id="form1Example1" class="form-control"
              v-model="username"
              :class="{'has-error' : submitting && invalidUsername}" />
            <label class="form-label" for="form1Example1">Login</label>
          </div>

          <!-- Password input -->
          <div class="form-outline mb-4">
            <input type="password" id="form1Example2" class="form-control" 
              v-model="repPassword"
              :class="{'has-error' : submitting && (invalidPassword || unproperPassword)}" />
            <label class="form-label" for="form1Example2">Hasło</label>
          </div>
          <p v-if="submitting && unproperPassword" class="error-message"> 
            Hasło musi posiadać minimum: 8 znaków, jedną małą literę, jedną wielką literę oraz liczbę.</p>
          <!-- Password input -->
          <div class="form-outline mb-4">
            <input type="password" id="form1Example2" class="form-control"
              v-model="password"
              :class="{'has-error' : submitting && (invalidPassword || unproperPassword)}"/>
            <label class="form-label" for="form1Example2">Powtórz hasło</label>
          </div>
          <p v-if="submitting && notSamePassword" class="error-message"> Powtórzone hasło jest błędne</p>
          <!-- 2 column grid layout for inline styling -->
          <!--<div class="row mb-4">
            <div class="col d-flex justify-content-center">
               Checkbox
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  value=""
                  id="form1Example3"
                  checked
                />
                <label class="form-check-label" for="form1Example3"> Accept policy </label>
              </div>
            </div>
          </div>-->
          <!-- Submit button -->
          <p v-if="error && submitting" class="error-message"> Proszę wypełnić odpowiednio pola formularza! </p>
          <p v-if="serverErr && submitting" class="error-message"> Taki użytkownik już istnieje.</p>
          <p v-if="success" class="success-message"> Dziękujemy za dołączone do PicPoster! Możesz się zalogować. </p>
          <button type="submit" class="btn btn-primary btn-block">Zarejestruj</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'register-component',
  data(){
    return{
      submitting: false,
      error: false,
      serverErr: false,
      success: false,
      email: "",
      username: "",
      password: "",
      repPassword: ""
    }
  },
  methods:
  {
    handleSubmit(){
      this.submitting = true;
      this.success = false;
      this.error = false;
      this.serverErr = false;

      if(this.invalidUsername || this.invalidPassword || this.invalidEmail || this.notSamePassword || this.unproperPassword){
        this.error = true;
        return
      }

      axios.post("http://localhost:8090/register", {
        login: this.username,
        password: this.password,
        email: this.email,
        profileImagePath: 'no-image.png'
      })
      .catch(e => {console.log(e); this.serverErr = true})
      .then(() => {
        if(this.serverErr){
          return
        }
        this.username = '';
        this.password = '';
        this.repPassword = '';
        this.email = '';
        this.error = false;
        this.success = true;
        this.submitting = false;
        this.serverErr = false;
      })
    }
  },
  computed:{
    invalidUsername(){
      return this.username === '';
    },
    invalidPassword(){
      return this.password === '' ;
    },
    invalidEmail(){
      return this.email === '';
    },
    notSamePassword(){
      return this.password != this.repPassword;
    },
    unproperPassword(){
      return !this.password.match(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/) //one number, one upper, one lower, 8 or more char
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>