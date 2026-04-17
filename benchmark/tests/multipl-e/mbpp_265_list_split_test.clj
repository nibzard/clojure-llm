(require '[clojure.test :refer [deftest is run-test]])

(def candidate list_split)

(deftest test-humaneval

  (is (= (candidate ["a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n"] 3) [["a" "d" "g" "j" "m"] ["b" "e" "h" "k" "n"] ["c" "f" "i" "l"]]))
  (is (= (candidate [1 2 3 4 5 6 7 8 9 10 11 12 13 14] 3) [[1 4 7 10 13] [2 5 8 11 14] [3 6 9 12]]))
  (is (= (candidate ["python" "java" "C" "C++" "DBMS" "SQL"] 2) [["python" "C" "DBMS"] ["java" "C++" "SQL"]]))
)

(run-test test-humaneval)