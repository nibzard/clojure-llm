(require '[clojure.test :refer [deftest is run-test]])

(def candidate insert_element)

(deftest test-humaneval

  (is (= (candidate ["Red" "Green" "Black"] "c") ["c" "Red" "c" "Green" "c" "Black"]))
  (is (= (candidate ["python" "java"] "program") ["program" "python" "program" "java"]))
  (is (= (candidate ["happy" "sad"] "laugh") ["laugh" "happy" "laugh" "sad"]))
)

(run-test test-humaneval)