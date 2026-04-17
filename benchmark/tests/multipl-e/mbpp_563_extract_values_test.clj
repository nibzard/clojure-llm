(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_values)

(deftest test-humaneval

  (is (= (candidate ""Python", "PHP", "Java"") ["Python" "PHP" "Java"]))
  (is (= (candidate ""python","program","language"") ["python" "program" "language"]))
  (is (= (candidate ""red","blue","green","yellow"") ["red" "blue" "green" "yellow"]))
)

(run-test test-humaneval)