(require '[clojure.test :refer [deftest is run-test]])

(def candidate replace_spaces)

(deftest test-humaneval

  (is (= (candidate "Jumanji The Jungle") "Jumanji_The_Jungle"))
  (is (= (candidate "The_Avengers") "The Avengers"))
  (is (= (candidate "Fast and Furious") "Fast_and_Furious"))
)

(run-test test-humaneval)