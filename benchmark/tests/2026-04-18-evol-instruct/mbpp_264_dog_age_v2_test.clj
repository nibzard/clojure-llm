(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:dog-years 3, :human-years 21, :puppy? false} (dog_age 3))))

(run-test test-variation)
