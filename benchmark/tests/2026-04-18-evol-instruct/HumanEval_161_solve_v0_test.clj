(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 "ba" nil "C"] (solve [1 "ab" nil "C"]))))

(run-test test-variation)
