import React from "react"
import axios from "axios";
import {BrowserRouter, Link, NavLink , Route , Routes} from "react-router-dom";

class LeagueTable extends React.Component {


    state = {

      teams:[{teamname:"" ,position:"",
    played:"" , won:"" , draw:"", lost:"", goalsFor:"",
     goalsAgainst:"", live:""}]
    
    }

    componentDidMount(){
      axios.get("http://localhost:8989/get-all-teams" ,{
        params:{

        }
      }).then((response)=>{
        const teamsFromServer = response.data;
       
        this.setState({
          teams:teamsFromServer
          
        })
        
      })
     
    }


    render (){
    return (
      
     <div>


          <table style={{color:"white",border: "1px solid white"}}>
             <tr>
                 <th style = {{padding:"10px"}}>position</th>
                 <th style = {{padding:"10px"}}>team</th>
                 <th style = {{padding:"10px"}}>played</th>
                 <th style = {{padding:"10px"}}>won</th>
                 <th style = {{padding:"10px"}}>draw</th>
                 <th style = {{padding:"10px"}}>lost</th>
                 <th style = {{padding:"10px"}}>goals_For</th>
                 <th style = {{padding:"10px"}}>goals_Against</th>
                 <th style = {{padding:"10px"}}>points</th>
             </tr>
          
             {
               this.state.teams.map((team)=>{
                         return(
          
            <tr>
                
                <td style = {{padding:"10px", color:"white"}}>{team.position}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.teamname}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.played}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.won}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.draw}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.lost}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.goalsFor}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.goalsAgainst}</td>
                <td style = {{padding:"10px", color:"white"}}>{team.points}</td>
               
            </tr>
                    
                         )
               })
            }
          </table>  


      
     </div>
    
    )
    }

 }
 export default LeagueTable;