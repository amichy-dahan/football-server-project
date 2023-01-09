import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Link, NavLink , Route , Routes} from "react-router-dom";
import LiveResult from './LiveResult';
import LeagueTable from './LeagueTable';

import Login from './Login';
import LeagueLive from './LeagueLive';

function App() {
  return (
    <center>
    <div >
    <div class="main">
      <BrowserRouter> 
                                        <li>
                                        <Link style={{ color: 'white' }} to={"/LiveResult"}>Live results</Link>
                                        </li>
                                        <li>
                                        <Link style={{ color: 'white' }} to={"/LeagueTable"}>League table</Link>
                                        </li>
                                        <li>
                                        <Link style={{ color: 'white' }} to={"/LeagueLive"}>League table live</Link>
                                        </li>
 
                                        <li>
                                       <Link style={{ color: 'white' }} to={"/Login"}>Login page</Link>
                                        </li>
                                     
                    <Routes>
                          <Route path={"/LiveResultv"} element={<LiveResult/>}/>
                          <Route path={"/LeagueTable"} element={<LeagueTable/>}/>
                          <Route path={"/LeagueLive"} element={<LeagueLive/>}/>
                          <Route path={"/Login"} element={<Login/>}/>
                         
                    </Routes>
      </BrowserRouter>
      </div>
    </div>
    </center>
  );
}

export default App;

