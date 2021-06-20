<template>
    <div id="login-form-container">
        <form @submit.prevent="handleSubmit">
          <!-- Email input -->
          <div class="form-outline mb-4">
            <input id="email-input" class="form-control" 
            v-model="username"
            :class="{'has-error' : submitting && invalidUsername}" />
            <label class="form-label" for="email-input">Login</label>
          </div>

          <!-- Password input -->
          <div class="form-outline mb-4">
            <input type="password" id="password-input" class="form-control" 
            v-model="password"
            :class="{'has-error' : submitting && invalidPassword}" />
            <label class="form-label" for="password-input">Hasło</label>
          </div>
          <p v-if="error && submitting" class="error-message"> Wypełnij wszystkie pola!</p>
          <p v-if="loginError && submitting" class="error-message"> Zły login lub hasło! Spróbuj ponownie. </p>
          <div id="spacer">

          </div>
          <div id="button-container">
            <!-- Submit button -->
            <button type="submit" class="btn btn-block">Zaloguj się</button>
          </div>
        </form>
    </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'login-component',
  data(){
    return {
      username: "",
      password: "",
      loginError: false,
      error: false,
      success: false,
      submitting: false,
    }
  },
  methods: {
    handleSubmit(){
      
      this.submitting = true;
      this.success = false;
      this.loginError = false;
      this.error = false;

      if(this.invalidUsername || this.invalidPassword){
        this.error = true;
        return;
      }

      axios.post("http://localhost:8090/authenticate", {
        username : this.username,
        password : this.password
      })
      .catch(e => { console.log(e); this.loginError = true;})
      .then((response) => {
        if(!this.loginError) {
          localStorage.setItem('token', response.data.jwt)
          this.setUserId(this.username)
          setTimeout(() => {
            this.$router.push(this.$route.query.redirect || '/wall')
            }, 450)
          }
        else {
          localStorage.removeItem('token')
          localStorage.removeItem('userId')
          return;
        }

        this.username = '';
        this.password = '';
        this.error = false;
        this.success = true;
        this.submitting = false;
        this.loginErr = false;
      })
    },

    setUserId(username){
       axios.get('http://localhost:8090/users/login/' + username, {headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}} )
      .then((response) => {localStorage.setItem('userId', response.data.id)})
      .catch(e => console.log(e))
    }
  },
  computed:{
    invalidUsername(){
      return this.username === '';
    },
    invalidPassword(){
      return this.password === '';
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #login-form-container {
    display: flex;
    height: 100%;
    width: 100%;

  }

#login-form-container form {
  display: flex;
  flex-direction: column;

  width: 100%;
  height: 100%;
}

#spacer {
  flex: 1;
}

#button-container {
  width: 100%;
  align-self: flex-end;
}

#button-container button {
  color: white;
  background-color: rgb(207, 25, 231);
}

#button-container button:hover {
  background-color: rgb(184, 29, 204);
}

</style>