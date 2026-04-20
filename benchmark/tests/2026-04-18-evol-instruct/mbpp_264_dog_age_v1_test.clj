(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:human 3 :dog 21 :stage :adult} (dog-age 3))))

(run-test test-variation)
