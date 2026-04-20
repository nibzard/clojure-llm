(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:even 4 :odd 2} (collatz-parity-counts 5)))
  (is (= {:even 0 :odd 1} (collatz-parity-counts 1)))
  (is (= {:even 4 :odd 2} (collatz-parity-counts 6))))

(run-test test-variation)
