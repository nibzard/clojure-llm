(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (upper-ctr ["AbC" "deF"])))
  (is (= 0 (upper-ctr nil)))
  (is (= 5 (upper-ctr ["hello" nil "WORLD"]))))

(run-test test-variation)
