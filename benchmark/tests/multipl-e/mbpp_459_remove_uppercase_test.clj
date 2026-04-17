(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_uppercase)

(deftest test-humaneval

  (is (= (candidate "cAstyoUrFavoRitETVshoWs") "cstyoravoitshos"))
  (is (= (candidate "wAtchTheinTernEtrAdIo") "wtchheinerntrdo"))
  (is (= (candidate "VoicESeaRchAndreComMendaTionS") "oiceachndreomendaion"))
)

(run-test test-humaneval)