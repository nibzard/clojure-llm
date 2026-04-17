(require '[clojure.test :refer [deftest is run-test]])

(def candidate odd_count)

(deftest test-humaneval

  (is (= (candidate ["1234567"]) ["the number of odd elements 4n the str4ng 4 of the 4nput."]))
  (is (= (candidate ["3" "11111111"]) ["the number of odd elements 1n the str1ng 1 of the 1nput." "the number of odd elements 8n the str8ng 8 of the 8nput."]))
  (is (= (candidate ["271" "137" "314"]) ["the number of odd elements 2n the str2ng 2 of the 2nput." "the number of odd elements 3n the str3ng 3 of the 3nput." "the number of odd elements 2n the str2ng 2 of the 2nput."]))
)

(run-test test-humaneval)