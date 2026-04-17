(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_if_last_char_is_a_letter)

(deftest test-humaneval

  (is (= (candidate "apple") false))
  (is (= (candidate "apple pi e") true))
  (is (= (candidate "eeeee") false))
  (is (= (candidate "A") true))
  (is (= (candidate "Pumpkin pie ") false))
  (is (= (candidate "Pumpkin pie 1") false))
  (is (= (candidate "") false))
  (is (= (candidate "eeeee e ") false))
  (is (= (candidate "apple pie") false))
  (is (= (candidate "apple pi e ") false))
)

(run-test test-humaneval)