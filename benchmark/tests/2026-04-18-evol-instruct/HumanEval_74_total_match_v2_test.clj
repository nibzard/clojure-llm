(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["hI" "Hi"] (total_match_lazy ["hi" "admin"] ["hI" "Hi"])))
  (is (= ["a" "bb"] (total_match_lazy ["a" "bb"] ["ccc"])))
  (is (= ["a" "a" "a"] (take 3 (total_match_lazy (repeat "a") ["bbbb"]))))
  (is (= ["x" "y"] (total_match_lazy ["x" "y"] ["zz"]))))

(run-test test-variation)
