<template>
  <div>
      <navbar-component />

      <div id="add-profile-image-page">
        <div id="add-profile-image-container" class="shadow-box">
            <div id="pi-profile-img" v-if="!imgPath && userImagePath">
                <image-component :filename="userImagePath"/>
            </div>  
            <div id="pi-profile-img" v-else-if="imgPath">
                <img :src="imgPath">
            </div>  
            <div id="pi-form">
                <form @submit.prevent="submitPost">
                    <div class="form-group">
                        <input type="file" class="form-control-file" id="add-post-input" 
                            accept="image/png, image/jpeg" @change="onFileChange">
                    </div>
                    <div id="pi-submit">
                        <button type="submit" class="btn btn-primary"
                            :class="{'btn-secondary disabled disabled-btn' : postFile === ''}">
                            Zapisz zdjęcie
                        </button>
                    </div>
                </form>
            </div>
        </div>
      </div>
  </div>
</template>

<script>
import NavbarComponent from '../components/NavbarComponent.vue'
import ImageComponent from '../components/ImageComponent.vue'
import axios from 'axios'

export default {
name: 'AddProfileImageView',
  components: {
    NavbarComponent,
    ImageComponent,
  },
  data(){
    return {
        imgPath: null,
        userImagePath: null,
        postFile: '',
        postDescription: "",
        postDisableComments: false,
    }
  },
  methods:{
    getUserImage(){
        axios.get('http://localhost:8090/users/' + localStorage.getItem('userId'), {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
        .then(data => {
            this.userImagePath = data.data.profileImagePath;
        })
        .catch(e => {
            console.log(e);
        })
    },

    onFileChange(e) {
        this.postFile = e.target.files[0];
        this.imgPath = URL.createObjectURL(this.postFile);
    },

    submitPost(){
        if(this.postFile != ''){

            let formData = new FormData();
            formData.append('imageFile', this.postFile);
            console.log('>> formData >> ', formData);

            axios({
                method: 'post',
                headers: { Authorization: 'Bearer ' + localStorage.getItem('token'),
                        'Content-Type': 'multipart/form-data' },
                url: "http://localhost:8090/upload",
                data: formData
            })
            .catch(e => {
                console.log(e);
                alert("Błąd ładowania zdjęcia!"); 

            })
            .then((response) => {
                if(response.status == 200){

                    axios.get('http://localhost:8090/users/' + localStorage.getItem('userId'), {
                        headers: {
                            Authorization: 'Bearer ' + localStorage.getItem('token')
                        }
                    })
                    .then(data => {
                        var updatedUser = data.data;
                        updatedUser['profileImagePath'] = response.data;
                        
                        axios.put("http://localhost:8090/users", 
                            updatedUser,
                            {
                                headers: {
                                    Authorization: 'Bearer ' + localStorage.getItem('token')
                                }
                            }
                        )
                        .catch(e => {console.log(e); alert("Zdjęcie profilowe nie może zostać zmienione!")})
                        .then((response) => {
                            if(response.status == 200){
                                this.$router.push({ name: 'ProfileView', params: { userId: localStorage.getItem('userId') } })
                            }
                        })

                    })
                    .catch(e => {
                        console.log(e);
                        alert("Nie znaleziono użytkownika!"); 
                    })

                }
            })
        }
    },

    goBack(){
        if(window.history.length > 1)
            this.$router.go(-1);
        else
            this.$router.push({ name: 'WallView' })
    },

    redirectIfLogout(){
      if(!localStorage.getItem('token')){
        this.$router.push(this.$route.query.redirect || '/')
      }
    }
  },
  created(){
    this.redirectIfLogout();
    this.getUserImage();
  },
}
</script>

<style>

#add-profile-image-page {
    display: flex;
    justify-content: center;
    align-items: center;
}

#add-profile-image-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
    padding: 20px;
    margin-bottom: 100px;

    width: 900px;
    background-color: white;
}

#pi-profile-img {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 450px;
    height: 450px;
    margin-bottom: 15px;
}

#pi-profile-img img {
    width: 400px;
    height: 400px;
    object-fit: cover;
    border-radius: 50%;
    border: 3px solid #ffffff;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
}

#pi-form {
    width: 80%;
}

#pi-form textarea {
    resize: none;
}

#pi-submit {
    display: flex;
    justify-content: center;
    align-items: center;

    width: 100%;
    margin-top: 5px;
}

#pi-submit .btn.disabled-btn {
    cursor: default;
    opacity: .4;
}

@media (max-width: 700px) { 
    #pi-profile-img {
        margin-right: 0;
        width: 70vw;
        height: 70vw;
    }

    #pi-profile-img img {
        width: 65vw;
        height: 65vw;
        object-fit: cover;
        border-radius: 50%;
        border: 3px solid #ffffff;
        -webkit-box-shadow: 1px 1px 4px #888888;
        -moz-box-shadow:    1px 1px 4px #888888;
        box-shadow:         1px 1px 4px #888888; 
    }
}

</style>