(require '[clojure.test :refer [deftest is run-test]])

(def candidate encode)

(deftest test-humaneval

  (is (= (candidate "TEST") "tgst"))
  (is (= (candidate "Mudasir") "mWDCSKR"))
  (is (= (candidate "YES") "ygs"))
  (is (= (candidate "This is a message") "tHKS KS C MGSSCGG"))
  (is (= (candidate "I DoNt KnOw WhAt tO WrItE") "k dQnT kNqW wHcT Tq wRkTg"))
)

(run-test test-humaneval)