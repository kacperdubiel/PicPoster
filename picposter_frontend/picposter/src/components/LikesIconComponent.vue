<template>
    <i id="likes-icon" class="fas fa-heart"
        v-bind:class="{ 'p-not-liked': !isLiked, 'p-liked': isLiked }"
    ></i>
</template>

<script>
import axios from "axios";

export default {
  name: 'likes-icon-component',
  props: {
      postId: String,
      userId: String
  },
  data(){
    return {
      isLiked: false
    }
  },
  methods:{
    isLikedColorChange(){
        axios.get('http://localhost:8090/likes/user/' + this.userId + '/post/' + this.postId)
        .then((response) => {
          if(response.status == 200)
            this.isLiked = true
        }).catch((err) => {console.log(err)});
    },

  },
  mounted(){
    this.isLikedColorChange()
  }
}
</script>

<style scoped>

#likes-icon {
  cursor: pointer;
}

#likes-icon:hover {
  color: rgb(255, 25, 71);
}

.p-not-liked {
    color: #888888;
}

.p-liked {
    color: rgb(236, 0, 47);
}

</style>