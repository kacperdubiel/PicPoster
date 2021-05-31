<template>
    <span :key="followingsAmount">{{ followingsAmount }}</span>
</template>

<script>
import axios from "axios";

export default {
  name: 'followings-counter-component',
  props: {
      userId: {
        type: String
      },
      isFollowingsAmountChanged: Boolean
      
  },
  data(){
    return {
      followingsAmount: 0
    }
  },
  watch: {
    'isFollowingsAmountChanged': function() {
      this.getUserFollowingsAmount();
    }
  },
  methods:{
    getUserFollowingsAmount(){
      axios.get('http://localhost:8090/follows/follower/' + this.userId)
        .then(data => {this.followingsAmount = data.data.length}).catch(e => alert(e))
    },

  },
  mounted(){
    this.getUserFollowingsAmount()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>