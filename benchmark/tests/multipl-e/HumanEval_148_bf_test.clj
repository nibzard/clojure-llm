(require '[clojure.test :refer [deftest is run-test]])

(def candidate bf)

(deftest test-humaneval

  (is (= (candidate "Jupiter" "Neptune") ["Saturn" "Uranus"]))
  (is (= (candidate "Earth" "Mercury") ["Venus"]))
  (is (= (candidate "Mercury" "Uranus") ["Venus" "Earth" "Mars" "Jupiter" "Saturn"]))
  (is (= (candidate "Neptune" "Venus") ["Earth" "Mars" "Jupiter" "Saturn" "Uranus"]))
  (is (= (candidate "Earth" "Earth") []))
  (is (= (candidate "Mars" "Earth") []))
  (is (= (candidate "Jupiter" "Makemake") []))
)

(run-test test-humaneval)