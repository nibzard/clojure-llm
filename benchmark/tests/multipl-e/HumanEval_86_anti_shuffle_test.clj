(require '[clojure.test :refer [deftest is run-test]])

(def candidate anti_shuffle)

(deftest test-humaneval

  (is (= (candidate "Hi") "Hi"))
  (is (= (candidate "hello") "ehllo"))
  (is (= (candidate "number") "bemnru"))
  (is (= (candidate "abcd") "abcd"))
  (is (= (candidate "Hello World!!!") "Hello !!!Wdlor"))
  (is (= (candidate "") ""))
  (is (= (candidate "Hi. My name is Mister Robot. How are you?") ".Hi My aemn is Meirst .Rboot How aer ?ouy"))
)

(run-test test-humaneval)