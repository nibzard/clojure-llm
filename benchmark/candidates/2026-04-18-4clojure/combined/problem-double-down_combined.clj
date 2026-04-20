(defn problem-double-down [a]
  (* 2 a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-double-down
  (is (= (= (problem-double-down 2) 4)))
  (is (= (= (problem-double-down 3) 6)))
  (is (= (= (problem-double-down 11) 22)))
  (is (= (= (problem-double-down 7) 14))))

(run-tests)
