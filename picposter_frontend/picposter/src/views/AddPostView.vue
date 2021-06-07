<template>
  <div>
      <navbar-component />

      <div id="add-post-page">
        <div id="add-post-container" class="shadow-box">
            <div id="ap-uploaded-img" v-if="!imgPath">
                <image-component :filename="defaultImgPath"/>
            </div>  
            <div id="ap-uploaded-img" v-else-if="imgPath">
                <img :src="imgPath">
            </div>  
            <div id="ap-form">
                <form @submit.prevent="submitPost">
                    <div class="form-group">
                        <!-- <label for="add-post-input">Dodaj zdjęcie</label> -->
                        <input type="file" class="form-control-file" id="add-post-input" 
                            accept="image/png, image/jpeg" @change="onFileChange">
                    </div>
                    <div class="form-group">
                        <label for="ap-description">Opis: </label>
                        <textarea class="form-control" id="ap-description" rows="3" maxlength="245"
                            v-model="postDescription"></textarea>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="disable-comments-check"
                            v-model="postDisableComments">
                        <label class="form-check-label" for="disable-comments-check">
                            Wyłącz możliwość komentowania
                        </label>
                    </div>
                    <div id="ap-submit">
                        <button type="submit" class="btn btn-primary"
                            :class="{'btn-secondary disabled disabled-btn' : postFile === ''}">
                            Dodaj zdjęcie
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
name: 'AddPostView',
  components: {
    NavbarComponent,
    ImageComponent,
  },
  data(){
    return {
        imgPath: null,
        defaultImgPath: "no-image.png",
        postFile: '',
        postDescription: "",
        postDisableComments: false,
    }
  },
  methods:{
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

                    axios.post("http://localhost:8090/posts", 
                        { 
                            imagePath: response.data,
                            description: this.postDescription,
                            allowComments: !this.postDisableComments,
                            poster: {
                                id: localStorage.getItem('userId')
                            }
                        },
                        {
                            headers: {
                                Authorization: 'Bearer ' + localStorage.getItem('token')
                            }
                        }
                    )
                    .catch(e => {console.log(e); alert("Post nie może zostać dodany!")})
                    .then((response) => {
                        if(response.status == 201){
                            this.$router.push({ name: 'PostView', params: { postId: response.data.id } })
                        }
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
    
  },
}
</script>

<style>

#add-post-page {
    display: flex;
    justify-content: center;
    align-items: center;
}

#add-post-container {
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

#ap-uploaded-img {
    max-height: 50vh;
    margin-bottom: 20px;
}

#ap-uploaded-img img {
    max-width: 100%;
    max-height: 50vh;
    border-radius: 10px;
}

#ap-form {
    width: 80%;
}

#ap-form textarea {
    resize: none;
}

#ap-submit {
    display: flex;
    justify-content: center;
    align-items: center;

    width: 100%;
    margin-top: 5px;
}

#ap-submit .btn.disabled-btn {
    cursor: default;
    opacity: .4;
}

</style>