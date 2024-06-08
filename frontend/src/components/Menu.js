import {Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';
import {Link, NavLink} from "react-router-dom";

const Menu = ({user}) => {
  return (
  <Navbar bg="light" expand="lg" style={{position: "fixed", width: '100vw', top: '0', left: '0'}}>
    <Container>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Navbar.Brand className="navbar-brandCustom" to="/">
          <NavLink className="nav-link" to="/">CubeMaze</NavLink>
        </Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Item>
            <NavLink className="nav-link" to="/scores">Scores</NavLink>
          </Nav.Item>
          <Nav.Item>
            <NavLink className="nav-link" to="/comments">Comments</NavLink>
          </Nav.Item>
          <Nav.Item>
            <NavLink className="nav-link" to="/ratings">Rating</NavLink>
          </Nav.Item>
          <Nav.Item>
            <NavLink className="nav-link" to="/game">Play the game</NavLink>
          </Nav.Item>
        </Nav>
        {user ? (
                <Nav>
                  <label className="label logged_user_display" >{user.email}</label>
                  <NavLink className="nav-link logout-link" to="/logout">Logout</NavLink>
                </Nav>
        ) : (
          <Nav>
          <Nav.Item >
          <NavLink className="nav-link login-link" to="/login">Login</NavLink>
          </Nav.Item>
          <Nav.Item>
          <NavLink className="nav-link signup-link" to="/register">Sign Up</NavLink>
          </Nav.Item>
          </Nav>)
        }

      </Navbar.Collapse>
    </Container>
  </Navbar>
    )
}

export default Menu;