import { Route, Routes } from 'react-router'
import './App.css'
import BookPage from './pages/Book/BookPage'
import Dashboard from './pages/Dashboard/Dashboard'
import MyLoans from './pages/MyLoans/MyLoans'
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
          <Route path='/my-reservations' element={<Dashboard />} />
          <Route path='/my-fines' element={<Dashboard />} />
          <Route path='/subscriptions' element={<Dashboard />} />
          <Route path='/wishlist' element={<Dashboard />} />
        </Route>

      </Routes>


    </>
  )
}

export default App
