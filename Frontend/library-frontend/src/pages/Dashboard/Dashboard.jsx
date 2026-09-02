import { AutoAwesome } from '@mui/icons-material';
import { Box, LinearProgress, Tab, Tabs } from '@mui/material';
import React from 'react';
import CurrentLoans from './CurrentLoans';
import ReadingHistory from './ReadingHistory';
import Recommendation from './Recommendation';
import Reservation from './Reservation';
import { statsConfig } from './StateConfig';
import StatesCard from './StatesCard';

const Dashboard = () => {

  const [activeTab, setActiveTab] = React.useState(0);

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  }

  const stateData = statsConfig({
    myLoans: [1, 2, 3],
    reservations: [1, 2],
    stats: { readingStreak: 5 }
  });

  return (
    <div className='min-h-screen bg-gradient-to-br from-indigo-50 via-white to 
    bg-purple-500 py-8'>
      <div className='px-4 sm:px-6 lg:px-8'>

        <div className='mb-8 animate-fade-in-up'>

          <h1 className='text-4xl font-bold text-indigo-500 mb-2'>

            My {" "} <span className='bg-gradient-to-r from-indigo-600 to-purple-600
            bg-clip-text text-transparent' >
              Dashboard
            </span>
          </h1>

          <p className='text-lg text-gray-600 bgColor' >
            Track Your Reading Journey And Manage Your Library
          </p>

        </div>

        {/* State Card */}

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {
            stateData.map((item) => <StatesCard
              bgColor={item.bgColor}
              textColor={item.textColor}
              icon={item.icon}
              value={item.value}
              title={item.title}
              subtitle={item.subtitle}
              key={item.id}
            />
            )
          }
        </div>

        {/* Reading Progress UI */}

        <div className='bg-white rounded-2xl shadow-2xl p-6 mb-8'>

          <div className='flex items-center justify-between mb-4' >

            <div>
              <h3 className='text-xl font-bold text-gray-900 mb-1'>
                Reading Goal
                <p className='text-gray-600 '>
                  {250} of 30 Books Read
                </p>
              </h3>
            </div>
            <div className='p-3 bg-gradient-to-br from-indigo-100 to-purple-100
               rounded-full rounded-full'>
              <AutoAwesome sx={{ fontSize: 32, color: "#4F46E5" }} />
            </div>

          </div>


          <LinearProgress variant='determinate' value={30}
            sx={{
              height: 12,
              borderRadius: 6,
              backgroundColor: "#E0E7FF",
              "& .MuiLinearProgress-bar": {
                background: "linear-gradient(90deg, #4F46E5 0%, #9333EA 100%",
                borderRadius: 6,
              },
            }}
          />

          <p className="text-sm text-gray-600"> 70% </p>

        </div>

        {/* {Tab Section} */}
        <div className='bg-white rounded-2xl shadow-2xl overflow-hidden'>
          <Box sx={{ BorderBottom: 1, BorderColor: 'divider' }}>
            <Tabs

              sx={{
                "& .MuiTab-root": {
                  textTransform: "none",
                  fontSize: "1rem",
                  fontWeight: 600
                },
                "& .Mui-selected": {
                  color: "#4F46E5",
                },
                "& .MuiTabs-indicator": {
                  backgroundColor: "#4F46E5",
                },
              }}

              value={activeTab} onChange={handleTabChange} aria-label="basic tabs example">
              <Tab label="Current Loans" />
              <Tab label="Reservations" />
              <Tab label="Reading History" />
              <Tab label="Recommendations" />
            </Tabs>
          </Box>

          {/*Current Loans Tab*/}
          {activeTab == 0 && <CurrentLoans />}

          {/* Reservations Tab */}
          {activeTab == 1 && <Reservation />}

          {/* Reading History Tab */}
          {activeTab == 2 && <ReadingHistory />}

          {/* Recommendations Tab */}
          {activeTab == 3 && <Recommendation />}




        </div>

      </div>
    </div>
  );
}

export default Dashboard
