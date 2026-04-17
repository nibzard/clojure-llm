(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_string)

(deftest test-humaneval

  (is (= (candidate ["Python" "list" "exercises" "practice" "solution"] 8) ["practice" "solution"]))
  (is (= (candidate ["Python" "list" "exercises" "practice" "solution"] 6) ["Python"]))
  (is (= (candidate ["Python" "list" "exercises" "practice" "solution"] 9) ["exercises"]))
)

(run-test test-humaneval)