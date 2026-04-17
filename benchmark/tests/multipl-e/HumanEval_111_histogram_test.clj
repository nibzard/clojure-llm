(require '[clojure.test :refer [deftest is run-test]])

(def candidate histogram)

(deftest test-humaneval

  (is (= (candidate "a b b a") {"a" 2 "b" 2}))
  (is (= (candidate "a b c a b") {"a" 2 "b" 2}))
  (is (= (candidate "a b c d g") {"a" 1 "b" 1 "c" 1 "d" 1 "g" 1}))
  (is (= (candidate "r t g") {"r" 1 "t" 1 "g" 1}))
  (is (= (candidate "b b b b a") {"b" 4}))
  (is (= (candidate "r t g") {"r" 1 "t" 1 "g" 1}))
  (is (= (candidate "") {}))
  (is (= (candidate "a") {"a" 1}))
)

(run-test test-humaneval)