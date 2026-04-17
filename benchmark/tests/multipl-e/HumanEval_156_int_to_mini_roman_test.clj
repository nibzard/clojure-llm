(require '[clojure.test :refer [deftest is run-test]])

(def candidate int_to_mini_roman)

(deftest test-humaneval

  (is (= (candidate 19) "xix"))
  (is (= (candidate 152) "clii"))
  (is (= (candidate 251) "ccli"))
  (is (= (candidate 426) "cdxxvi"))
  (is (= (candidate 500) "d"))
  (is (= (candidate 1) "i"))
  (is (= (candidate 4) "iv"))
  (is (= (candidate 43) "xliii"))
  (is (= (candidate 90) "xc"))
  (is (= (candidate 94) "xciv"))
  (is (= (candidate 532) "dxxxii"))
  (is (= (candidate 900) "cm"))
  (is (= (candidate 994) "cmxciv"))
  (is (= (candidate 1000) "m"))
)

(run-test test-humaneval)