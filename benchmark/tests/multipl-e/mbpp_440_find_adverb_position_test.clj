(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_adverb_position)

(deftest test-humaneval

  (is (= (candidate "clearly!! we can see the sky") [0 7 "clearly"]))
  (is (= (candidate "seriously!! there are many roses") [0 9 "seriously"]))
  (is (= (candidate "unfortunately!! sita is going to home") [0 13 "unfortunately"]))
)

(run-test test-humaneval)