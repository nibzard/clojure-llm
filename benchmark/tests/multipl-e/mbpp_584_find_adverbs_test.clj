(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_adverbs)

(deftest test-humaneval

  (is (= (candidate "Clearly, he has no excuse for such behavior.") "0-7: Clearly"))
  (is (= (candidate "Please handle the situation carefuly") "28-36: carefuly"))
  (is (= (candidate "Complete the task quickly") "18-25: quickly"))
)

(run-test test-humaneval)