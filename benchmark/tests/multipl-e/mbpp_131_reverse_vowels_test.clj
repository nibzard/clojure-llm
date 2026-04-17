(require '[clojure.test :refer [deftest is run-test]])

(def candidate reverse_vowels)

(deftest test-humaneval

  (is (= (candidate "Python") "Python"))
  (is (= (candidate "USA") "ASU"))
  (is (= (candidate "ab") "ab"))
)

(run-test test-humaneval)