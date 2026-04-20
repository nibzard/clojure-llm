(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (pal-stack [1 2 3 2 1])))
  (is (= true (pal-stack '(a b b a))))
  (is (= false (pal-stack [1 2 3])))
  (is (= true (pal-stack nil))))

(run-test test-variation)
