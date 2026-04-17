(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_nth_element)

(deftest test-humaneval

  (is (= (candidate [["Greyson Fulton" 98 99] ["Brady Kent" 97 96] ["Wyatt Knott" 91 94] ["Beau Turnbull" 94 98]] 0) ["Greyson Fulton" "Brady Kent" "Wyatt Knott" "Beau Turnbull"]))
  (is (= (candidate [["Greyson Fulton" 98 99] ["Brady Kent" 97 96] ["Wyatt Knott" 91 94] ["Beau Turnbull" 94 98]] 2) [99 96 94 98]))
  (is (= (candidate [["Greyson Fulton" 98 99] ["Brady Kent" 97 96] ["Wyatt Knott" 91 94] ["Beau Turnbull" 94 98]] 1) [98 97 91 94]))
)

(run-test test-humaneval)