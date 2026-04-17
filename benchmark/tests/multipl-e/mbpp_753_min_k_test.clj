(require '[clojure.test :refer [deftest is run-test]])

(def candidate min_k)

(deftest test-humaneval

  (is (= (candidate [["Manjeet" 10] ["Akshat" 4] ["Akash" 2] ["Nikhil" 8]] 2) [["Akash" 2] ["Akshat" 4]]))
  (is (= (candidate [["Sanjeev" 11] ["Angat" 5] ["Akash" 3] ["Nepin" 9]] 3) [["Akash" 3] ["Angat" 5] ["Nepin" 9]]))
  (is (= (candidate [["tanmay" 14] ["Amer" 11] ["Ayesha" 9] ["SKD" 16]] 1) [["Ayesha" 9]]))
)

(run-test test-humaneval)