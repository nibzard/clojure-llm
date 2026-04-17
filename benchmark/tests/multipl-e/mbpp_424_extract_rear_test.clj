(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_rear)

(deftest test-humaneval

  (is (= (candidate ["Mers" "for" "Vers"]) ["s" "r" "s"]))
  (is (= (candidate ["Avenge" "for" "People"]) ["e" "r" "e"]))
  (is (= (candidate ["Gotta" "get" "go"]) ["a" "t" "o"]))
)

(run-test test-humaneval)