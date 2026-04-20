(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (total-match-nil-safe nil nil)))
  (is (= ["a" "bc"] (total-match-nil-safe ["a" "bc"] nil)))
  (is (= ["hi"] (total-match-nil-safe ["hi"] ["a" "b"])))
  (is (= ["ab"] (total-match-nil-safe ["ab"] ["c" "d"]))))

(run-test test-variation)
