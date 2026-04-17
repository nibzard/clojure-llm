(require '[clojure.test :refer [deftest is run-test]])

(def candidate prime_length)

(deftest test-humaneval

  (is (= (candidate "Hello") true))
  (is (= (candidate "abcdcba") true))
  (is (= (candidate "kittens") true))
  (is (= (candidate "orange") false))
  (is (= (candidate "wow") true))
  (is (= (candidate "world") true))
  (is (= (candidate "MadaM") true))
  (is (= (candidate "Wow") true))
  (is (= (candidate "") false))
  (is (= (candidate "HI") true))
  (is (= (candidate "go") true))
  (is (= (candidate "gogo") false))
  (is (= (candidate "aaaaaaaaaaaaaaa") false))
  (is (= (candidate "Madam") true))
  (is (= (candidate "M") false))
  (is (= (candidate "0") false))
)

(run-test test-humaneval)