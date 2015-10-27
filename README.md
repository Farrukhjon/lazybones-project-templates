Lazybones template project
--------------------------
- In oder to install template project to local cache type: `./gradlew installAllTemplates`
- In oder to package template project type: `./gradlew packageAllTemplates` 

In order to use, just add its bintray repository path `farrukh/lazybones-templates` to your lazybones config file:
`
{
    "bintrayRepositories": [
        "farrukh/lazybones-templates",
        "pledbrook/lazybones-templates",
        "kyleboon/lazybones"
    ]
}

`

You can find out more about creating templates on [the GitHub wiki][1].

[1]: https://github.com/pledbrook/lazybones/wiki/Template-developers-guide
