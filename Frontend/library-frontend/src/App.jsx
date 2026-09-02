import { Route, Routes } from 'react-router'
import './App.css'
import Dashboard from './pages/Dashboard/Dashboard'
import UserLayout from './pages/UserLayout/UserLayout'

function App() {

  return (
    <>

      <Routes>
        {/* User Routes */}
        <Route element={<UserLayout />} >
          <Route path='/' element={<Dashboard />} />
        </Route>

      </Routes>

    </>
  )
}

export default App
