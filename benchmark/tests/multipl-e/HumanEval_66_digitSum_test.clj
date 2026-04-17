(require '[clojure.test :refer [deftest is run-test]])

(def candidate digitSum)

(deftest test-humaneval

  (is (= (candidate "") 0))
  (is (= (candidate "abAB") 131))
  (is (= (candidate "abcCd") 67))
  (is (= (candidate "helloE") 69))
  (is (= (candidate "woArBld") 131))
  (is (= (candidate "aAaaaXa") 153))
  (is (= (candidate " How are yOu?") 151))
  (is (= (candidate "You arE Very Smart") 327))
)

(run-test test-humaneval)