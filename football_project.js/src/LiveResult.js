import React, { createContext } from "react"

import {BrowserRouter, Link, NavLink , Route , Routes} from "react-router-dom";
import axios from "axios";
class LiveResult extends React.Component {
    


  state = {
 
         games:[],
         team1:"",
         team2:"",
    }

    
    

  

      render(){
        
              return(
               <div>

               </div>
              )

      }
       


}
export default LiveResult;



























































































































































































    // state = {

//         teams:[{teamname:""}], // bar , nadrid...


//         teamsGame:[],// t1 , t2
        
//         scores:[],   // st1 , st2

        
//           singin:false ,//dont forget change false

//           folder :false,

//           team1:"",
//           team2:"",
//           index:1,


//           scoreA:"",
//           scoreB:"",



//           goalsA:"",
//           gaolsB:"" ,

          

//           liveGame:false,

//           timeLese:""

      


// };

//   componentDidMount(){
//         axios.get("http://localhost:8989/get-all-teamName" ,{
//           params:{
  
//           }
//         }).then((response)=>{
//           const teamsFromServer = response.data;
//           console.log(teamsFromServer);
//           this.setState({
//             teams:teamsFromServer
            
//           })
          
//         })
  
//       }

//           onScore1 = (event ) => {
//               this.setState({
//                   scoreA: event.target.value
               
//               })
//             }
            
//             onScore2 = (event ) => {
//               this.setState({
//                   scoreB: event.target.value
                  
//               })
//             }


//     startLiveGame = () => {
//         const newGame = {
          
//           team1 :this.state.team1,
//           team2 :this.state.team2,
//           score1:0,
//           score2:0

//         }
//         const crruntUsers = this.state.teamsGame;
//         console.log(this.state.teamsGame);
//         crruntUsers.push(newGame);
//         this.setState({
//           teamsGame:crruntUsers ,
//           liveGame:true,
          
          
//         })
//         axios.get("http://localhost:8989/newGame" ,{
//           params:newGame
//           })
  
  
  
//       };

//                 OnClickTeam1 = (event) =>{
                        
//                           this.setState({
//                           team1:event.target.value,
                          
//                       }) 
                   
   
//             }
//                 OnClickTeam2 = (event) =>{
                                      
//                   this.setState({
//                   team2:event.target.value
                  
//                   })
                  
//             }
//             gameScores =()=>{
//                const lists = this.state.teamsGame;
//                this.setState({

//                })
              
//               const GameScore = {
//                 scoreA :this.state.scoreA,
//                 scoreB :this.state.scoreB ,
//                 a: this.state.team1,
//                 b: this.state.team2
//               }
              
//               const crruntUsers = this.state.scores;
//               crruntUsers.push(GameScore);
//               this.setState({
//                 scores:crruntUsers ,
//               })
//               axios.get("http://localhost:8989/scores" ,{
//                 params:GameScore
//                 })

                
              
//             }
  
//             finishGame=()=>{
//               this.setState({
//               liveGame:false
//               })
  
//             }

          


        
              

//     render () {

      
//    return (
    
// <center>

  

//     <div>
      


// <li>
//                                         <Link style={{ color: 'white' }} to={"/page2"}>League table</Link>
//                                         </li>
//                                         <li>
//                                         <Link style={{ color: 'white' }} to={"/page3"}>League table live</Link>
//                                         </li>
               
               
//                 <div style={{color:"white"}}>
//                       <h3>Choose teams for live game</h3>
//                       <br></br>
//                     <table>

                      
//                           <tr>
//                             <th>team 1</th> 
//                             <th>team 2</th> 
//                           </tr>                                                  
//                       <tr>
//                             <th>

                              
//                             <select name="teams" id="team"  onChange={this.OnClickTeam1}>
      
//                            <option value="">choose team</option>
//                             {
                              
//                     this.state.teams.map((team)=>{
//                               return(
//                                 <option value={team.teamname} >{team.teamname}</option>
//                               )
//                     })
//                   }
//                             </select>
    
//                             </th>
//                      <th>
//                       <select name="teams" id="team"  onChange={this.OnClickTeam2}>
//                       <option value="team">choose team</option>
//                       {
                        
//                this.state.teams.map((team)=>{
//                          return(
//                           <option value={team.teamname}>{team.teamname}</option>
//                          )
//                })
               
//             }
//                       </select> <br></br>
//                       </th>
//                       </tr>
//                       <button  onClick={this.startLiveGame}>start live game</button> <br></br><br></br>
//                 </table>
                
//                       </div>

                      
//                   {
//                        this.state.teamsGame.length> 0  &&               
//                        <div style={{color:"white"}}>

//               {                
//                  this.state.teamsGame.map((teamGame , index)=> {

//                             return(

//                                     <table>
//                                         <tr>
//                                           <th>
//                                                <input placeholder="goal"value={teamGame.scoreA} onChange={this.onScore1}> </input> 
//                                           </th>
//                                           <th>
//                                                <input placeholder="goal" value={teamGame.scoreB}onChange= {this.onScore2}></input> 
//                                           </th>
//                                           <th>
//                                                <button onClick={this.gameScores}>update</button> 
//                                           </th>
//                                           <th>
//                                               <button onClick={this.finishGame}>finish game</button> 
//                                           </th>
//                                       </tr>
//                                       <tr>
//                                           <th>
//                                                <p key={index}>{teamGame.team1} </p>
//                                           </th>
//                                           <th>
//                                               <p>{teamGame.team2}</p>
//                                           </th>
//                                       </tr>
                                        
//                                     </table>
//                             )

//                           })
                          
//                         }
//                         </div> 
//     }
    
           
                          
//             </div>   
                    
// </center>
            
//    )
// }
