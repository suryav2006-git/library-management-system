import { Route, Routes } from 'react-router'
import './App.css'
import BookPage from './pages/Book/BookPage'
import Dashboard from './pages/Dashboard/Dashboard'
import MyFine from './pages/MyFine/MyFine'
import MyLoans from './pages/MyLoans/MyLoans'
import MyReservation from './pages/MyReservation/MyReservation'
import Profile from './pages/Profile/Profile'
import Settings from './pages/Settings/Settings'
import Subscription from './pages/Subscription/Subscription'
import UserLayout from './pages/UserLayout/UserLayout'

function App() {

  return (
    <>
      {/* User Routes */}
      <Routes>

        <Route element={<UserLayout />} >
          <Route path='/' element={<Dashboard />} />
          <Route path='/books' element={<BookPage />} />
          <Route path='/my-loans' element={<MyLoans />} />
          <Route path='/my-reservations' element={<MyReservation />} />
          <Route path="/my-fines" element={<MyFine />} />
          <Route path='/subscriptions' element={<Subscription />} />
          <Route path='/wishlist' element={<Dashboard />} />
          <Route path="/profile" element={<Profile />} />
          <Route path='/settings' element={<Settings />} />

        </Route>

      </Routes>


    </>
  )
}

export default App
