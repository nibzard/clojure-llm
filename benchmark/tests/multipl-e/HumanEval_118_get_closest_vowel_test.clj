(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_closest_vowel)

(deftest test-humaneval

  (is (= (candidate "yogurt") "u"))
  (is (= (candidate "full") "u"))
  (is (= (candidate "easy") ""))
  (is (= (candidate "eAsy") ""))
  (is (= (candidate "ali") ""))
  (is (= (candidate "bad") "a"))
  (is (= (candidate "most") "o"))
  (is (= (candidate "ab") ""))
  (is (= (candidate "ba") ""))
  (is (= (candidate "quick") ""))
  (is (= (candidate "anime") "i"))
  (is (= (candidate "Asia") ""))
  (is (= (candidate "Above") "o"))
)

(run-test test-humaneval)