import { AutoAwesome } from '@mui/icons-material';

import {
  Box,
  LinearProgress,
  Tab,
  Tabs
} from '@mui/material';

import React from 'react';

import CurrentLoans from './CurrentLoans';
import ReadingHistory from './ReadingHistory';
import Recommendation from './Recommendation';
import Reservation from './Reservation';

import { statsConfig } from './StateConfig';
import StatesCard from './StatesCard';


const Dashboard = () => {

  const [activeTab, setActiveTab] = React.useState(0);


  // Tab Navigation

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };


  // Dashboard Statistics

  const stateData = statsConfig({
    myLoans: [1, 2, 3],
    reservations: [1, 2],
    stats: {
      readingStreak: 5
    }
  });


  // Reading Goal

  const readingGoal = 30;
  const booksRead = 25;

  const readingPercentage = Math.min(
    (booksRead / readingGoal) * 100,
    100
  );


  return (

    <Box
      sx={{
        minHeight: "100vh",
        width: "100%",
        background: "linear-gradient(135deg, #f8fafc 0%, #eef2ff 100%)",
        pb: 4
      }}
    >

      <Box
        sx={{
          width: "100%",
          maxWidth: "1600px",
          mx: "auto",
          px: {
            xs: 2,
            sm: 3,
            md: 3
          },
          py: 3
        }}
      >


        {/* PAGE HEADER */}

        <Box sx={{ mb: 4 }}>

          <h1 className="text-3xl sm:text-4xl font-bold text-gray-900 mb-2">

            My{" "}

            <span className="bg-gradient-to-r from-indigo-600 to-purple-600 bg-clip-text text-transparent">

              Dashboard

            </span>

          </h1>


          <p className="text-gray-600 text-base sm:text-lg">

            Track your reading journey and manage your library.

          </p>

        </Box>



        {/* STATISTICS CARDS */}

        <Box
          sx={{
            display: "grid",
            gridTemplateColumns: {
              xs: "1fr",
              sm: "repeat(2, 1fr)",
              lg: "repeat(4, 1fr)"
            },
            gap: 2.5,
            mb: 4
          }}
        >

          {stateData.map((item) => (

            <StatesCard

              key={item.id}

              bgColor={item.bgColor}

              textColor={item.textColor}

              icon={item.icon}

              value={item.value}

              title={item.title}

              subtitle={item.subtitle}

            />

          ))}

        </Box>



        {/* READING PROGRESS */}

        <Box
          sx={{
            backgroundColor: "#ffffff",
            borderRadius: 4,
            p: {
              xs: 2.5,
              sm: 3
            },
            mb: 4,
            boxShadow: "0 4px 20px rgba(15, 23, 42, 0.06)",
            border: "1px solid #e2e8f0"
          }}
        >

          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              mb: 3
            }}
          >

            <Box>

              <h3 className="text-xl font-bold text-gray-900 mb-1">

                2026 Reading Goal

              </h3>


              <p className="text-gray-600 text-sm">

                {booksRead} of {readingGoal} books read.

              </p>

            </Box>


            <Box
              sx={{
                p: 1.5,
                borderRadius: "50%",
                background: "linear-gradient(135deg, #eef2ff, #f3e8ff)"
              }}
            >

              <AutoAwesome
                sx={{
                  fontSize: 30,
                  color: "#4F46E5"
                }}
              />

            </Box>

          </Box>



          <LinearProgress

            variant="determinate"

            value={readingPercentage}

            sx={{
              height: 11,
              borderRadius: 10,
              backgroundColor: "#e0e7ff",

              "& .MuiLinearProgress-bar": {
                borderRadius: 10,

                background: "linear-gradient(90deg, #6366f1 0%, #9333ea 100%)"
              }
            }}

          />


          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              mt: 1
            }}
          >

            <p className="text-sm text-gray-500">

              Reading Progress

            </p>


            <p className="text-sm font-semibold text-indigo-600">

              {Math.round(readingPercentage)}%

            </p>

          </Box>

        </Box>



        {/* DASHBOARD TABS */}

        <Box
          sx={{
            backgroundColor: "#ffffff",
            borderRadius: 4,
            overflow: "hidden",
            boxShadow: "0 4px 20px rgba(15, 23, 42, 0.06)",
            border: "1px solid #e2e8f0"
          }}
        >

          <Box
            sx={{
              borderBottom: "1px solid #e2e8f0",
              px: 1,
              overflowX: "auto"
            }}
          >

            <Tabs

              value={activeTab}

              onChange={handleTabChange}

              variant="scrollable"

              scrollButtons="auto"

              allowScrollButtonsMobile

              sx={{

                "& .MuiTab-root": {
                  textTransform: "none",
                  fontSize: "0.9rem",
                  fontWeight: 600,
                  minHeight: 55,
                  minWidth: "auto",
                  px: 2
                },

                "& .Mui-selected": {
                  color: "#4F46E5 !important"
                },

                "& .MuiTabs-indicator": {
                  backgroundColor: "#4F46E5",
                  height: 3,
                  borderRadius: 3
                }

              }}

            >

              <Tab label="Current Loans" />

              <Tab label="Reservations" />

              <Tab label="Reading History" />

              <Tab label="Recommendations" />

            </Tabs>

          </Box>



          {/* TAB CONTENT */}

          <Box sx={{ p: { xs: 1.5, sm: 2 } }}>

            {activeTab === 0 && <CurrentLoans />}

            {activeTab === 1 && <Reservation />}

            {activeTab === 2 && <ReadingHistory />}

            {activeTab === 3 && <Recommendation />}

          </Box>


        </Box>


      </Box>

    </Box>

  );

};


export default Dashboard;