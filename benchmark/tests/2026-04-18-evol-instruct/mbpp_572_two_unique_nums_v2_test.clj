(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \b \c \a] (vec (unique-in-order "aaabcca")))))

(run-test test-variation)
