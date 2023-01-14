import React from "react";
import {BrowserRouter, Link, NavLink , Route , Routes} from "react-router-dom";
import './App.css';
import axios from "axios";
import {sendApiGetRequest, sendApiPostRequest} from "./ApiRequests";
import LiveResult from "./LiveResult";





class Login extends React.Component {
      
      state = {
        teams:[{teamname:"" ,position:"",
        played:"" , won:"" , draw:"", lost:"", goalsFor:"",
         goalsAgainst:"", live:""}],
              users:[] ,
              users1:[], 
            
                username:"",
                password:"",
                username1:"",
                password1:"",
                singin:false,


                sendGoalA:"",
                sendGoalB:"",

                games:[],
                team1:"",
                team2:"",

            teamsA:[],
            teamsB:[]

           


      };

      


    componentDidMount(){
      axios.get("http://localhost:8989/get-all-teams" ,{
        params:{

        }
      }).then((response)=>{
        const teamsFromServer = response.data;
        this.setState({
          teams:teamsFromServer
          
        })
        console.log(this.state.teams)
        
      })
          axios.get("http://localhost:8989/get-all-games" ,{
        params:{

        }
      }).then((response)=>{
        const teamsFromServer = response.data;
        console.log(teamsFromServer);
        this.setState({
          games:teamsFromServer
        
        })
        
      })
      console.log(this.state.games)

    }

  

    changep = (e) =>{
       this.setState({
        nameY:e.target.value
       })

    }



handleteam1=(e)=>{
  const o = e.target.value;
    this.setState({
      team1:o,
     
    })
    
   

}
handleteam2=(e)=>{

  this.setState({
    team2:e.target.value

  })
  
}


startGame = () =>{
   const lists = this.state.games;
  if(this.state.team1 != "" || this.state.team2 != ""){
 const game = { 
      teamA:this.state.team1,
      teamB:this.state.team2,
      goalsA:0,
      goalsB:0,
      live:true
   }
   lists.push(game);
   this.setState({
    games:lists,
   })

   axios.get("http://localhost:8989/newGame" ,{
    params:game
    })
  }
  
   console.log(this.state.games)
}

s
finishGame =(finish)=>{

  const gameFinish = {
    team1toF:finish.teamA,
    team2toF:finish.teamB,
    goalsAf:finish.goalsA,
    goalsBf:finish.goalsB,
  }
  axios.get("http://localhost:8989/finish-game" ,{
    params:gameFinish
      
    })

}

onChange1(newName, index) {
 this.state.games[index].goalsA = newName;
 const ca = this.state.games[index].goalsA
 this.setState({
  colorA:ca,
  sendGoalA:ca
 })
 
}


onChange2(newName, index) {
  this.state.games[index].goalsB = newName;
  const cb = this.state.games[index].goalsB
  this.setState({
    colorB:cb,
    sendGoalB:cb
   })
  
 }


 updateChange =(newUpdate,index) =>{

 const gameUpdate = {
    team1Update:newUpdate.teamA,
    team2Update:newUpdate.teamB,
    goalsAUp:newUpdate.goalsA,
    goalsBUp:newUpdate.goalsB,
  }
  axios.get("http://localhost:8989/update-game" ,{
    params:gameUpdate
      
    })
    console.log(gameUpdate)

 }
   
 

            onUserNameChange = (event) => {
              this.setState({
                  username: event.target.value
              })
            }
       
            onPassChange = (event) => {
              this.setState({
                  password: event.target.value
              })
            }
            onUserNameChange1 = (event) => {
              this.setState({
                  username1: event.target.value
              })
            }
       
            onPassChange1 = (event) => {
              this.setState({
                  password1: event.target.value
              })
            }


       addUserToArray = () => {
      const newUser = {
        username :this.state.username,
        password :this.state.password
      }
      const crruntUsers = this.state.users;
      crruntUsers.push(newUser);
     
      this.setState({
        users:crruntUsers ,
        username:"",
        password:""

      })
      axios.get("http://localhost:8989/create-account" ,{
        params:newUser
        })
    };

 

  
    checkUser = () => {
      const newUser1 = {
          username1 :this.state.username1,
          password1 :this.state.password1
        }
        const crruntUsers1 = this.state.users1;
        crruntUsers1.push(newUser1);
        this.setState({
          users1:crruntUsers1 ,
          username1:"",
          password1:""
        })
       
        axios.get("http://localhost:8989/sign-in" ,{
          params:newUser1
          }
      ).then((res)=>{
        const bool = res.data.success;
        if(bool == true) {
          this.setState({
            singin:true
          })
        }
        else
        {
          this.setState({
            singin:false
          })
        }
      })
    }; 

         

    render(){
  
    const teamsinGameA = this.state.teams.filter(team => this.state.games.every(game => team.teamname != game.teamA && team.teamname != game.teamB))
    const teamsinGameB = this.state.teams.filter(team => this.state.games.every(game => team.teamname != game.teamB && team.teamname != game.teamA)) 


   
      const teamsA = teamsinGameA.filter(t => t.teamname!= this.state.team2)
      const teamsB = teamsinGameB.filter(t => t.teamname!= this.state.team1)
  
      
    return (

      
    <center>
            <div>
            <LiveResult name="RETYRT"/>
         {         
                   this.state.singin? (

                    
                     <p style={{color:"white"}}>

                          welcom!!!!!!!!!!!!!!!!
                      <br></br> <br></br> 

                                        <div style={{color:"white"}}>

<h3>Choose teams for live game</h3>
<select  name="team" onChange={this.handleteam1}>

  <option> -select team--</option>
  {
    teamsA.map((team , index)=>(
      <option  value={team.teamname} key={index}>{team.teamname}</option>
   
    ))
    
  }
                                    
</select> 
<select  name="team" onChange={this.handleteam2}>

          <option> -select team--</option>
          {
            teamsB.map((team , index)=>(
              <option value={team.teamname} key={index}>{team.teamname}</option>
            ))
          }
                  
</select> 
                                
<button onClick={this.startGame}>start</button>



{this.state.games.length > 0 && 
<div>
{                
 this.state.games.map((game,index)=> {

            return(

                    <table>
                        <tr>
                          <th>
                               <input key={index} placeholder="goal" onChange={(e) => this.onChange1(e.target.value, index)}></input> 
                          </th>
                          <th>
                          <input key={index} placeholder="goal" onChange={(e) => this.onChange2(e.target.value, index)}></input><br></br>
                          
                          </th>
                          <th>
                               <button key={index}onClick={(e) => this.updateChange(game, index)}>update</button> 
                          </th>
                          <th>
                              <button key={index}onClick={(e) => this.finishGame(game, index)}>finishGame</button> 
                          </th>
                      </tr>
                      <tr>
                          <th>
                               <p value={game.teamA} style={{color:game.goalsA > game.goalsB ? "green" :game.goalsA == game.goalsB ? "yellow":"red"}}>{game.teamA}</p>
                          </th>
                          <th>
                              <p value={game.teamB} style={{color:game.goalsB  > game.goalsA  ? "green" :game.goalsA == game.goalsB ? "yellow":"red"}}>{game.teamB}</p>
                          </th>
                      </tr>
                        
                    </table>
                    
            )

          })
          
        }
</div>
}


</div>
                                        

      
                     
               </p>
                   ): (
                    <div style={{color:"white"}}>
              
                    <h4>welcome!</h4>
                    <p style={{color:"white"}}>create account <br/>

                        <input placeholder="username" value={this.state.username} onChange={this.onUserNameChange}></input><br/>
                        <input type={"password"} placeholder="password"value={this.state.password} onChange={this.onPassChange} ></input><br/>
                        <button onClick={this.addUserToArray} disabled={this.state.username.length==0 || this.state.password==0}>create</button>

                    </p>

                    <p style={{color:"white"}}>sing in <br/>

                        <input placeholder="username" value={this.state.username1} onChange={this.onUserNameChange1}></input><br/>
                        <input type={"password"} placeholder="password"value={this.state.password1} onChange={this.onPassChange1} ></input><br/>
                        <button onClick={this.checkUser} disabled={this.state.username1.length==0 || this.state.password1==0}>sing in</button>

                    </p>

                    </div>
                   )
                }
          </div>
          </center>
    )
 }
}
 export default Login;




























  {/* <ul>
             
              <li>
                <NavLink style={{ color: 'white' }} to={"/page1"}>Live results</NavLink>
                </li>
                <li>
                <NavLink style={{ color: 'white' }} to={"/page2"}>League table</NavLink>
                </li>
                <li>
                <NavLink style={{ color: 'white' }} to={"/page3"}>League table live</NavLink>
                </li>
              </ul> */}