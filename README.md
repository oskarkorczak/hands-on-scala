# [Hands-on Scala Programming][handsonbook]
Scala exercises based on Li Haoyi's "Hands-on Scala Programming" book. 

## Running through [Ammonite][ammonite]
Ammonite can run simple Scala script files, which is any file containing Scala code, ending in `.scala`.

### Basic Script Run
Run script once:

```
 amm  script.scala
```

### Watching Scripts
If you are working on single script, you can use `amm --watch` or `amm -w` to watch the script and re-run it when things change.

```
amm -w script.scala
```
Exit by pressing `Ctrl-C`.

### Scripts loading to REPL
You can open up a REPL with access to the functions in Scala script by using `--predef` flag.

```
 amm --predef  script.scala
```

Given `script.scala` has `helloWorld` method defined, then it could be accessed in REPL.

```
// script.scala
def helloWorld(name: String) = {
   s"Hello World, $name"
}
...
$> amm --predef script.scala
@> helloWorld("Jonny")
@> res0: String = "Hello World, Jonny"
``` 

### Load to REPL and Watch
If you need to edit large script file in the editor, but still want to test it in REPL, you may combine `--watch` and `--predef` flags, as follows:

```
amm -w --predef script.scala
```

If you make changes to the script file, you need to exit the REPL using `Ctrl-D` and `-w` flag will make sure to restart the REPL upon change. 




[handsonbook]: https://www.handsonscala.com
[ammonite]: https://ammonite.io/




