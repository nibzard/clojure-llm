(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_bored)

(deftest test-humaneval

  (is (= (candidate "Hello world") 0))
  (is (= (candidate "Is the sky blue?") 0))
  (is (= (candidate "I love It !") 1))
  (is (= (candidate "bIt") 0))
  (is (= (candidate "I feel good today. I will be productive. will kill It") 2))
  (is (= (candidate "You and I are going for a walk") 0))
)

(run-test test-humaneval)